import { Client } from "../clients/client";
import { Pet } from "../model/pet";

export class Appointment {
    constructor(
        public id?: number,
        public scheduleDate?: string,
        public petId?: number,
        public reason?: string,
        public meds?: string,
        public note?: string,
        public complete?: boolean
    ) { }
}

export class PetClientAppt {
    constructor(
        public appointment: Appointment,
        public apptClient: Client,
        public apptPet: Pet
    ) { }
}
