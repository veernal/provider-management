import { Address } from "./Address";
import { License } from "./License";

export interface Provider {

    id:number;
    firstName:string;
    middleName:string;
    lastName:string;
    gender:string;
    dba:string;
    classification:string;
    dob:string;
    providerType:string;
    speciality:string;
    active:string;
    userId:string;
    businessUserId:number;
    address: Address;
    license: License;
    

}