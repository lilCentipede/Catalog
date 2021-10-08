
import { ItemSchema } from "./item-schema";
import { ItemType } from "./item-type";

export class Item{
    id : String;
    name : String;
    description : String;
    type : ItemType;
    projectIds : String[];
    createdAt : String;
    createdBy: String;
    lastUpdatedAt : Date;
    lastUpdatedBy : String;
    //schema : ItemSchema;
    iconID : String;
    bulkRequestLimit : Number;

}