package com.app.service;

import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//vamos a implementar para crear nuestro propio detailsService
@Service
public class UserDetailServiceImpl  implements UserDetailsService {

    @Autowired//porque necesitamos acceder a los usuarios de nuestra bdd
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario "+username+ " no existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();//spring gestiona los permisos
        //aqui se viene la programacion funiconal
        userEntity.getRoles()
                //con esto estamos tomando los roles ya sea admin,developer , los estamos convirtiendo a un SimpleGrantedAuthority
                //para decirle a springSecurity mira este rol existe haz lo quieras con el
                .forEach( role -> authorityList.add(  new SimpleGrantedAuthority( "ROLE_".concat(role.getRoleEnum().name()) )) );

                //ahora nos falta agregar los permisos

        userEntity.getRoles().stream()
                //recorriendo los persmisos que estan dentro de los roles
                .flatMap( role -> role.getPermissionList().stream())
                .forEach( permissionEntity -> authorityList.add( new SimpleGrantedAuthority(  permissionEntity.getName() ) ) );


        //con esto le estamos diciendo a spring security que busque los usuarios en bdd , que tome los permisos
        //roles, y los convierta a objetos que entiende spring security y devolvemos el usuario , pero un objeto
        //que entiende spring security
        //user entity pasamos a User <-user es una clase de spring security

        return  new User(
                userEntity.getUsername() ,
                userEntity.getPassword(),
                userEntity.isEnable(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList
        );
    }



}
