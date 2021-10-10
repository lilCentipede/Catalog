
import { ItemSchema } from "./item-schema";
import { ItemType } from "./item-type";

export class Item{
    id : string;
    name : string;
    description : string;
    type : ItemType;
    projectIds : string[];
    createdAt : string;
    createdBy: string;
    lastUpdatedAt : Date;
    lastUpdatedBy : string;
    schema : ItemSchema;
    iconID : string;
    bulkRequestLimit : Number;
}