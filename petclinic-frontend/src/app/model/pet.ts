import { Client } from "../clients/client";

export class Pet {

    constructor(
        public id?: number,
        public name?: string,
        public age?: number,
        public weight?: number,
        public animalType?: string,
        public ownerId?: number
    ) { }
}
