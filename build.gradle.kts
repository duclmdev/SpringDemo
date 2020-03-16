import org.gradle.internal.jvm.Jvm
import org.gradle.internal.os.OperatingSystem

apply {
    from("$rootDir/gradle/config.gradle.kts")
}
val os = extra["os"] as OperatingSystem

println("Building '${rootProject.name}' on ${os.name} ver ${os.version} with ${Jvm.current()}...")

repositories {
    mavenCentral()
}

plugins { java }

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

subprojects {
    apply(plugin = "java")

    repositories { mavenCentral() }

    val project = this

    tasks.register<Copy>("copyLibs") {
        doFirst {
            println("Copy dependencies of module ${"%-15s".format(project.name)} into $rootDir/libs/${project.name}...")
        }

        from(configurations.default)
        into("$rootDir/libs/${project.name}")

        doLast {
            println("Done copying dependencies of module ${project.name}!")
        }
    }

    tasks.register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    buildDir = File("${if (os.isWindows) "D:" else "/var"}/Temp/Compiled/${rootProject.name}/${project.name}")
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        doFirst {
            println(" - Module ${"%-12s".format(project.name)} -> $buildDir")
        }
    }

    dependencies {
        val lombokV = "1.18.12"
        // implementation(fileTree("/libs"))

        implementation("org.slf4j:slf4j-api:1.7.26")
        implementation("ch.qos.logback:logback-core:1.2.3")
        implementation("ch.qos.logback:logback-classic:1.2.3")

        compileOnly("org.projectlombok:lombok:$lombokV")
        annotationProcessor("org.projectlombok:lombok:$lombokV")

        testImplementation("junit:junit:4.12")
        testAnnotationProcessor("org.projectlombok:lombok:$lombokV")
    }
}
