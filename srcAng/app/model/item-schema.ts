import { Input } from "@angular/core";

export class ItemSchema{
   
        type: String;
        encrypted: boolean;
        properties: Map<String,Input>;
        required: String[];
}