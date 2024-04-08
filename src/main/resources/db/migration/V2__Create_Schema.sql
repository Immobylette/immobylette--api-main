CREATE TABLE "leases" (
                          "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                          "creation_date" date NOT NULL,
                          "rental_start_date" date NOT NULL,
                          "nb_keys" int NOT NULL DEFAULT 1,
                          "end_date" date NOT NULL,
                          "fk_tenant" uuid NOT NULL,
                          "fk_owner" uuid NOT NULL,
                          "fk_property" uuid NOT NULL
);

CREATE TABLE "third_parties" (
                                 "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                 "surname" varchar(100) NOT NULL,
                                 "name" varchar(100) NOT NULL,
                                 "ref_photo" uuid,
                                 "birth_date" date NOT NULL,
                                 "bank_details" varchar(34) NOT NULL,
                                 "social_security_number" varchar(15) NOT NULL,
                                 "fk_third_party_type" uuid NOT NULL
);

CREATE TABLE "properties" (
                              "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                              "creation_date" date NOT NULL,
                              "surface" float NOT NULL,
                              "nb_rooms" int NOT NULL,
                              "ref_photo" uuid NOT NULL,
                              "ref_photo_folder" uuid,
                              "fk_hot_water_type" uuid NOT NULL,
                              "fk_heating_type" uuid NOT NULL,
                              "fk_property_type" uuid NOT NULL,
                              "fk_property_class" uuid NOT NULL,
                              "fk_third_party_owner" uuid NOT NULL,
                              "fk_address" uuid NOT NULL
);

CREATE TABLE "addresses" (
                             "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                             "number" int NOT NULL,
                             "street" varchar(100) NOT NULL,
                             "zip" int NOT NULL,
                             "city" varchar(100) NOT NULL,
                             "floor" int,
                             "extra" varchar(20),
                             "latitude" float,
                             "longitude" float
);

CREATE TABLE "third_party_types" (
                                     "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                     "label" varchar(100) NOT NULL
);

CREATE TABLE "heating_types" (
                                 "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                 "label" varchar(100) NOT NULL
);

CREATE TABLE "hot_water_types" (
                                   "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                   "label" varchar(100) NOT NULL
);

CREATE TABLE "property_types" (
                                  "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                  "label" varchar(100) NOT NULL
);

CREATE TABLE "property_classes" (
                                    "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                    "label" varchar(100) NOT NULL
);

CREATE TABLE "inventories" (
                               "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                               "inventory_date" date,
                               "fk_inventory_type" uuid NOT NULL,
                               "fk_lease" uuid NOT NULL,
                               "fk_third_party" uuid NOT NULL
);

CREATE TABLE "inventory_types" (
                                   "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                   "label" varchar(100) NOT NULL
);

CREATE TABLE "steps" (
                         "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                         "description" varchar(255) DEFAULT null,
                         "error_description" varchar(255) DEFAULT null,
                         "ref_photos_folder" uuid NOT NULL,
                         "fk_inventory" uuid NOT NULL,
                         "fk_state_type" uuid NOT NULL,
                         "fk_element" uuid NOT NULL
);

CREATE TABLE "state_types" (
                               "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                               "label" varchar(100) NOT NULL
);

CREATE TABLE "elements" (
                            "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                            "attributes" hstore,
                            "ref_photo" uuid NOT NULL,
                            "ref_photos_folder" uuid NOT NULL,
                            "description" varchar(255),
                            "fk_parent_element" uuid,
                            "fk_element_type" uuid NOT NULL,
                            "fk_room" uuid NOT NULL
);

CREATE TABLE "element_types" (
                                 "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                 "label" varchar(100) NOT NULL
);

CREATE TABLE "element_attributes" (
                                      "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                                      "label" varchar(100) NOT NULL
);

CREATE TABLE "rooms" (
                         "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                         "description" varchar(255) DEFAULT null,
                         "area" float NOT NULL,
                         "nb_walls" int NOT NULL,
                         "nb_doors" int NOT NULL,
                         "nb_windows" int NOT NULL,
                         "reference" varchar(100),
                         "number_order" int NOT NULL,
                         "fk_allocation" uuid NOT NULL,
                         "fk_room_type" uuid NOT NULL,
                         "fk_property" uuid NOT NULL
);

CREATE TABLE "allocations" (
                               "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                               "label" varchar(100) NOT NULL
);

CREATE TABLE "room_types" (
                              "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
                              "label" varchar(100) NOT NULL
);

CREATE TABLE "signatures_leases_third_parties" (
                                                   "id_lease" uuid NOT NULL,
                                                   "id_third_party" uuid NOT NULL,
                                                   "signature_date" date,
                                                   PRIMARY KEY ("id_lease", "id_third_party")
);

CREATE TABLE "signatures_inventories_third_parties" (
                                                        "id_inventory" uuid NOT NULL,
                                                        "id_third_party" uuid NOT NULL,
                                                        "signature_date" date,
                                                        PRIMARY KEY ("id_inventory", "id_third_party")
);

CREATE TABLE "element_attributes_element_types" (
                                                    "id_element_attribute" uuid NOT NULL,
                                                    "id_element_type" uuid NOT NULL,
                                                    PRIMARY KEY ("id_element_attribute", "id_element_type")
);

ALTER TABLE "third_parties" ADD FOREIGN KEY ("fk_third_party_type") REFERENCES "third_party_types" ("id");

ALTER TABLE "properties" ADD FOREIGN KEY ("fk_heating_type") REFERENCES "heating_types" ("id");

ALTER TABLE "properties" ADD FOREIGN KEY ("fk_hot_water_type") REFERENCES "hot_water_types" ("id");

ALTER TABLE "properties" ADD FOREIGN KEY ("fk_property_type") REFERENCES "property_types" ("id");

ALTER TABLE "properties" ADD FOREIGN KEY ("fk_property_class") REFERENCES "property_classes" ("id");

ALTER TABLE "inventories" ADD FOREIGN KEY ("fk_inventory_type") REFERENCES "inventory_types" ("id");

ALTER TABLE "properties" ADD FOREIGN KEY ("fk_third_party_owner") REFERENCES "third_parties" ("id");

ALTER TABLE "signatures_leases_third_parties" ADD FOREIGN KEY ("id_lease") REFERENCES "leases" ("id");

ALTER TABLE "signatures_leases_third_parties" ADD FOREIGN KEY ("id_third_party") REFERENCES "third_parties" ("id");

ALTER TABLE "inventories" ADD FOREIGN KEY ("fk_lease") REFERENCES "leases" ("id");

ALTER TABLE "inventories" ADD FOREIGN KEY ("fk_third_party") REFERENCES "third_parties" ("id");

ALTER TABLE "signatures_inventories_third_parties" ADD FOREIGN KEY ("id_inventory") REFERENCES "inventories" ("id");

ALTER TABLE "signatures_inventories_third_parties" ADD FOREIGN KEY ("id_third_party") REFERENCES "third_parties" ("id");

ALTER TABLE "steps" ADD FOREIGN KEY ("fk_state_type") REFERENCES "state_types" ("id");

ALTER TABLE "steps" ADD FOREIGN KEY ("fk_inventory") REFERENCES "inventories" ("id");

ALTER TABLE "elements" ADD FOREIGN KEY ("fk_parent_element") REFERENCES "elements" ("id");

ALTER TABLE "elements" ADD FOREIGN KEY ("fk_element_type") REFERENCES "element_types" ("id");

ALTER TABLE "rooms" ADD FOREIGN KEY ("fk_allocation") REFERENCES "allocations" ("id");

ALTER TABLE "rooms" ADD FOREIGN KEY ("fk_room_type") REFERENCES "room_types" ("id");

ALTER TABLE "rooms" ADD FOREIGN KEY ("fk_property") REFERENCES "properties" ("id");

ALTER TABLE "elements" ADD FOREIGN KEY ("fk_room") REFERENCES "rooms" ("id");

ALTER TABLE "steps" ADD FOREIGN KEY ("fk_element") REFERENCES "elements" ("id");

ALTER TABLE "element_attributes_element_types" ADD FOREIGN KEY ("id_element_attribute") REFERENCES "element_attributes" ("id");

ALTER TABLE "element_attributes_element_types" ADD FOREIGN KEY ("id_element_type") REFERENCES "element_types" ("id");

ALTER TABLE "leases" ADD FOREIGN KEY ("fk_tenant") REFERENCES "third_parties" ("id");

ALTER TABLE "leases" ADD FOREIGN KEY ("fk_owner") REFERENCES "third_parties" ("id");

ALTER TABLE "leases" ADD FOREIGN KEY ("fk_property") REFERENCES "properties" ("id");

ALTER TABLE "properties" ADD FOREIGN KEY ("fk_address") REFERENCES "addresses" ("id");
