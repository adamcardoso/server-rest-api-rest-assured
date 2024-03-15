<div align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-Ff0000?style=for-the-badge&logo=coffeescript&logoColor=white">
  <img alt="rest-assured" src="https://img.shields.io/badge/rest--assured-512DA8?style=for-the-badge&logo=rest-assured&logoColor=white">
</div>


# Projeto de Estudos - ServerRest Api ☕

Repositório para alocar os testes automatizados de API com Rest-Assured que foram desenvolvidos durante o projeto de estudos.

## Links
- [Repositório Server Rest API](https://github.com/ServeRest/ServeRest)


## Pré-requisitos ⚙️

- [Intellij Idea](https://www.jetbrains.com/idea/)
- [JDK 17](https://www.oracle.com/java/technologies/downloads/)
- [Rest-Assured](https://rest-assured.io)




## Dependências Utilizadas 👀

```pom.xml
<properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <lombok.version>1.18.30</lombok.version>
        <rest-assured.version>5.4.0</rest-assured.version>
        <json-validator.version>5.4.0</json-validator.version>
        <junit5.version>5.10.2</junit5.version>
        <allure.version>2.20.1</allure.version>
        <datafaker.version>2.1.0</datafaker.version>
        <allure-junit5.version>2.25.0</allure-junit5.version>
        <jackson-databind.version>2.17.0</jackson-databind.version>
        <maven-compiler-plugin.version>3.8.1</maven-compiler-plugin.version>
        <maven-surefire-plugin.version>2.22.2</maven-surefire-plugin.version>
        <junit-surefire.version>1.3.2</junit-surefire.version>
        <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
        <aspectj.version>1.9.7</aspectj.version>
        <allure-maven.version>2.10.0</allure-maven.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${rest-assured.version}</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit5.version}</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>${json-validator.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson-databind.version}</version>
        </dependency>
        <dependency>
            <groupId>net.datafaker</groupId>
            <artifactId>datafaker</artifactId>
            <version>${datafaker.version}</version>
        </dependency>
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-junit5</artifactId>
            <version>${allure-junit5.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

# Sistema de Teste Automatizado

## Ferramentas e Tecnologias Utilizadas

- **Rest-Assured**: Uma ferramenta para automação de testes de api.
- **Intellij**
