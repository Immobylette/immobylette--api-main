ALTER TABLE "rooms"
ADD "name" VARCHAR(255);

UPDATE "rooms"
SET "name" = '';

ALTER TABLE "rooms"
ALTER COLUMN "name" SET NOT NULL;