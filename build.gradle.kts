import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("org.flywaydb.flyway") version "8.0.1"
	id("nu.studer.jooq") version "7.1.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//	implementation("org.flywaydb:flyway-core") // spring 2.7だと相性が悪いらしい
//	implementation("org.flywaydb:flyway-mysql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.mysql:mysql-connector-j")
	jooqGenerator("mysql:mysql-connector-java")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	url = "jdbc:mysql://localhost:3306/todo?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false"
	user = "mysql"
	password = "password"
}

jooq {
	configurations {
		create("main") {
			jooqConfiguration.apply {
				jdbc.apply {
					url = "jdbc:mysql://localhost:3306/todo?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false"
					user = "mysql"
					password = "password"
				}
				generator.apply {
					name = "org.jooq.codegen.KotlinGenerator"
					database.apply {
						name = "org.jooq.meta.mysql.MySQLDatabase"
						inputSchema = "todo"
						excludes = "flyway_schema_history"
					}
					generate.apply {
						isDeprecated = false
						isTables = true
					}
					target.apply {
						packageName = "com.example.springJooqSample.infra.jooq"
						directory = "${buildDir}/generated/source/jooq/main"
					}
				}
			}
		}
	}
}

