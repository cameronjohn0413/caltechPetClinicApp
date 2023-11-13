import { Pet } from "../model/pet";

export class Client {

    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
        public phone?: string,
        public pets?: Array<Pet>
    ) { }
}
