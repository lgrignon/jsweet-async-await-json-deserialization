# JSweet Model/DTO JSON deserialization + async / await

Sample code showing how to properly deserialize JSON data (retrieved with fetch) to plain Java model / DTO objects.
It is worth noting the usage of the async / await idiom with JSweet along with the fetch API

## Usage

```
> git clone https://github.com/lgrignon/jsweet-model-dto-json-deserialization.git
> cd jsweet-model-dto-json-deserialization
> mvn generate-sources
> firefox www/index.html
```

## Prerequisites

- Java 8 JDK is required. Type in ``java -version`` in a console to make sure that you have a >1.8 JDK.
- The `node` and `npm` executables must be in the path (https://nodejs.org).
- Install Maven (https://maven.apache.org/install.html).
