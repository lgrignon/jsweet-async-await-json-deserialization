/* Generated from Java with JSweet 2.2.0-SNAPSHOT - http://www.jsweet.org */
var org;
(function (org) {
    var jsweet;
    (function (jsweet) {
        var examples;
        (function (examples) {
            var deserialization;
            (function (deserialization) {
                class JsonModelDeserializer {
                    constructor() {
                        if (this.modelPackage === undefined)
                            this.modelPackage = null;
                        let modelClassName = (c => c["__class"] ? c["__class"] : c["name"])(org.jsweet.examples.deserialization.model.User);
                        this.modelPackage = modelClassName.substring(0, modelClassName.lastIndexOf("."));
                        console.info("model package: " + this.modelPackage);
                    }
                    decode(jsonList) {
                        return JSON.parse(jsonList, (key, value) => {
                            let valueObject = value;
                            if (valueObject != null && valueObject.hasOwnProperty("__type")) {
                                let type = (valueObject["__type"]);
                                let className = this.modelPackage + "." + type;
                                let constructor = this.resolveConstructor(className);
                                let typedValue = Object.assign(new constructor(), valueObject);
                                console.info("turned value into: (type=" + type + ")");
                                console.info(typedValue);
                                return typedValue;
                            }
                            else {
                                return valueObject;
                            }
                        });
                    }
                    /*private*/ resolveConstructor(className) {
                        let constructor = window;
                        {
                            let array158 = className.split(".");
                            for (let index157 = 0; index157 < array158.length; index157++) {
                                let part = array158[index157];
                                {
                                    constructor = (constructor[part]);
                                }
                            }
                        }
                        return constructor;
                    }
                }
                deserialization.JsonModelDeserializer = JsonModelDeserializer;
                JsonModelDeserializer["__class"] = "org.jsweet.examples.deserialization.JsonModelDeserializer";
            })(deserialization = examples.deserialization || (examples.deserialization = {}));
        })(examples = jsweet.examples || (jsweet.examples = {}));
    })(jsweet = org.jsweet || (org.jsweet = {}));
})(org || (org = {}));
(function (org) {
    var jsweet;
    (function (jsweet) {
        var examples;
        (function (examples) {
            var deserialization;
            (function (deserialization) {
                var model;
                (function (model) {
                    class Profile {
                        constructor() {
                            this.__type = (c => c["__class"] ? c["__class"].substring(c["__class"].lastIndexOf('.') + 1) : c["name"].substring(c["name"].lastIndexOf('.') + 1))(Profile);
                            if (this.name === undefined)
                                this.name = null;
                        }
                    }
                    model.Profile = Profile;
                    Profile["__class"] = "org.jsweet.examples.deserialization.model.Profile";
                })(model = deserialization.model || (deserialization.model = {}));
            })(deserialization = examples.deserialization || (examples.deserialization = {}));
        })(examples = jsweet.examples || (jsweet.examples = {}));
    })(jsweet = org.jsweet || (org.jsweet = {}));
})(org || (org = {}));
(function (org) {
    var jsweet;
    (function (jsweet) {
        var examples;
        (function (examples) {
            var deserialization;
            (function (deserialization) {
                var model;
                (function (model) {
                    class User {
                        constructor() {
                            this.__type = (c => c["__class"] ? c["__class"].substring(c["__class"].lastIndexOf('.') + 1) : c["name"].substring(c["name"].lastIndexOf('.') + 1))(User);
                            if (this.name === undefined)
                                this.name = null;
                            if (this.active === undefined)
                                this.active = false;
                            if (this.age === undefined)
                                this.age = null;
                            if (this.profile === undefined)
                                this.profile = null;
                        }
                    }
                    model.User = User;
                    User["__class"] = "org.jsweet.examples.deserialization.model.User";
                })(model = deserialization.model || (deserialization.model = {}));
            })(deserialization = examples.deserialization || (examples.deserialization = {}));
        })(examples = jsweet.examples || (jsweet.examples = {}));
    })(jsweet = org.jsweet || (org.jsweet = {}));
})(org || (org = {}));
(function (org) {
    var jsweet;
    (function (jsweet) {
        var examples;
        (function (examples) {
            var deserialization;
            (function (deserialization) {
                class ModelDeserializationExample {
                    constructor() {
                        if (this.htmlOutput === undefined)
                            this.htmlOutput = null;
                        this.htmlOutput = document.querySelector("#htmlOutput");
                        fetch(ModelDeserializationExample.MODEL_JSON_URL).then((response) => {
                            if (!response.ok) {
                                console.log("cannot fetch model from JSON file: " + ModelDeserializationExample.MODEL_JSON_URL);
                                this.htmlOutput.style.color = "rgb(200, 100, 100)";
                                this.htmlOutput.innerHTML = "cannot fetch model from JSON file: " + ModelDeserializationExample.MODEL_JSON_URL;
                            }
                            else {
                                console.log("model loaded from JSON file: " + ModelDeserializationExample.MODEL_JSON_URL);
                                this.htmlOutput.style.color = "black";
                                this.htmlOutput.innerHTML = "model loaded from JSON file: " + ModelDeserializationExample.MODEL_JSON_URL;
                                response.text().then((modelJson) => {
                                    this.onJsonModelAvailable(modelJson);
                                    return null;
                                }).catch((error) => { return this.onError(error); });
                            }
                            return null;
                        }).catch((error) => { return this.onError(error); });
                    }
                    /*private*/ onJsonModelAvailable(modelListJson) {
                        console.log("JSON model: " + modelListJson);
                        let deserializer = new org.jsweet.examples.deserialization.JsonModelDeserializer();
                        let users = deserializer.decode(modelListJson);
                        console.log("users deserialized");
                        console.log(users);
                        this.htmlOutput.style.color = "rgb(100, 200, 100)";
                        this.htmlOutput.innerHTML += "<br />users: " + (a => a ? '[' + a.join(', ') + ']' : 'null')(users);
                        for (let i = 0; i < users.length; i++) {
                            this.htmlOutput.innerHTML += "<br />user #" + (i + 1) + " is User?: " + (users[i] != null && users[i] instanceof org.jsweet.examples.deserialization.model.User);
                            this.htmlOutput.innerHTML += "<br />user #" + (i + 1) + "\'s profile is Profile?: " + (users[i].profile != null && users[i].profile instanceof org.jsweet.examples.deserialization.model.Profile);
                        }
                        ;
                    }
                    /*private*/ onError(error) {
                        this.htmlOutput.style.color = "rgb(200, 100, 100)";
                        this.htmlOutput.innerHTML = "An error occurred: " + error;
                        return null;
                    }
                    static main(...args) {
                        new ModelDeserializationExample();
                    }
                }
                ModelDeserializationExample.MODEL_JSON_URL = "model.json";
                deserialization.ModelDeserializationExample = ModelDeserializationExample;
                ModelDeserializationExample["__class"] = "org.jsweet.examples.deserialization.ModelDeserializationExample";
            })(deserialization = examples.deserialization || (examples.deserialization = {}));
        })(examples = jsweet.examples || (jsweet.examples = {}));
    })(jsweet = org.jsweet || (org.jsweet = {}));
})(org || (org = {}));
org.jsweet.examples.deserialization.ModelDeserializationExample.main(null);
