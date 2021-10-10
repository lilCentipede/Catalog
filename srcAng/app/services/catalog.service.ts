import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  of } from 'rxjs';
import { mergeAll, concatMap ,map, concatAll} from 'rxjs/operators';
import { Item } from '../model/item';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  public mainServerAddress : String = "http://localhost:8081";

  constructor(public http : HttpClient) { }

  retrieveCatalog(plusServerAddress : String = "catalog/items")  : Observable<Item[]>{
    return this.http.get<Item[]>(`${this.mainServerAddress}/${plusServerAddress}`,{observe:'body',responseType: 'json'});
  }
  retrieveItemByID(id : String,plusServerAddress : String = "/catalog/items")  : Observable<Item>{
    return this.http.get<Item>(`${this.mainServerAddress}/${plusServerAddress}/${id}`,{observe:'body',responseType: 'json'});
  }
   retrieveWholeCatalog(plusServerAddress : String = "catalog")  : Observable<Item[]>{
    return this.http.get<Item[]>(`${this.mainServerAddress}/${plusServerAddress}`,{observe:'body',responseType: 'json'});
  }
  makeDeployment(body : any,plusServerAddress : String = "catalog/deployment") : void{
    this.http.post<any>(`${this.mainServerAddress}/${plusServerAddress}`,body,{observe:'body',responseType: 'json'})
          .subscribe(data => {
            console.log(data);
        });
  }
}