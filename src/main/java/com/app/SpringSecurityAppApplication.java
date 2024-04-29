package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);


	}
	//se ejecuta inmediatamente se levanta la aplicacion
	//ejecutamos codigo aqui para poblar nuestra tablas
	@Bean
	CommandLineRunner init( UserRepository userRepository ){//inyectamdo el userRepository
//userRepository es el que se encargara de registrar todos nuestros usuarios en la tabla
		return  args -> {
			//1.-PRIMERO CREAMOS LOS PERMISOS con ayuda de nuestra clase permissionEntity
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			//2.-SEGUNDO CREAMOS LOS ROLES
			RoleEntity rolAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
					.build();

			RoleEntity rolUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission,readPermission))
					.build();


			RoleEntity rolInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();


			RoleEntity rolDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission ,refactorPermission))
					.build();




			//3.-TERCER PASO CREAMOS LOS USUARIOS
			UserEntity userSantiago = UserEntity.builder()
					.username("santiago")
					.password("$2a$10$lyuqGKcFokQoxxLt.BHUYOq28AusT0Lpos4DhHemG173e.CQPeAOO")
					.isEnable(true)//la cuenta esta activo
					.accountNoExpired(true)//la cuenta no ha expirado
					.accountNoLocked(true)//la cuenta no esta bloqueada
					.credentialNoExpired(true)//creedenciales no estan expiradas
					.roles(Set.of(rolAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password("$2a$10$lyuqGKcFokQoxxLt.BHUYOq28AusT0Lpos4DhHemG173e.CQPeAOO")
					.isEnable(true)//la cuenta esta activo
					.accountNoExpired(true)//la cuenta no ha expirado
					.accountNoLocked(true)//la cuenta no esta bloqueada
					.credentialNoExpired(true)//creedenciales no estan expiradas
					.roles(Set.of(rolUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("andrea")
					.password("$2a$10$lyuqGKcFokQoxxLt.BHUYOq28AusT0Lpos4DhHemG173e.CQPeAOO")
					.isEnable(true)//la cuenta esta activo
					.accountNoExpired(true)//la cuenta no ha expirado
					.accountNoLocked(true)//la cuenta no esta bloqueada
					.credentialNoExpired(true)//creedenciales no estan expiradas
					.roles(Set.of(rolInvited))
					.build();

			UserEntity userAnyi = UserEntity.builder()
					.username("anyi")
					.password("$2a$10$lyuqGKcFokQoxxLt.BHUYOq28AusT0Lpos4DhHemG173e.CQPeAOO")
					.isEnable(true)//la cuenta esta activo
					.accountNoExpired(true)//la cuenta no ha expirado
					.accountNoLocked(true)//la cuenta no esta bloqueada
					.credentialNoExpired(true)//creedenciales no estan expiradas
					.roles(Set.of(rolDeveloper))
					.build();

			//guarado los usuarios en la bd , levantamos la aplicacion y se guardaran
			userRepository.saveAll(List.of( userSantiago , userDaniel , userAndrea , userAnyi ));

		};
	}




}
