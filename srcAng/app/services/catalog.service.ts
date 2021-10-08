import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../model/item';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  public mainServerAddress : String = "http://localhost:8081";

  constructor(public http : HttpClient) { }

  retrieveItemByID(id : String,plusServerAddress : String = "/catalog/items")  : Observable<Item>{
    return this.http.get<Item>(`${this.mainServerAddress}/${plusServerAddress}/${id}`,{observe:'body',responseType: 'json'});
  }
  retrieveCatalog(plusServerAddress : String = "catalog/items")  : Observable<Item[]>{
      return this.http.get<Item[]>(`${this.mainServerAddress}/${plusServerAddress}`,{observe:'body',responseType: 'json'});
  }
  retrieveHello() :Observable<String>{
    return this.http.get<String>("localhost:8081/catalog/hello",{});
  }
  
  retrieveWholeCatalog(plusServerAddress : String = "catalog")  : Observable<Item[]>{
    return this.http.get<Item[]>(`${this.mainServerAddress}/${plusServerAddress}`,{observe:'body',responseType: 'json'});
  }


  retrieveFake() : Observable<Item[]>{
    return this.http.get<Item[]>("assets/fake.json",{observe:'body',responseType: 'json'});
  }
  retrieveFake2() : Observable<Item[]>{
    return this.http.get<Item[]>("assets/fake2.json",{observe:'body',responseType: 'json'});
  }
  retrieveFake3()  : Observable<Item[]>{
    return this.http.get<Item[]>("assets/fake3.json",{observe:'body',responseType: 'json'});
  }
}
