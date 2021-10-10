import { Component } from '@angular/core';
import { Item } from './model/item';
import { CatalogService } from './services/catalog.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ServiceBrokerCatalog';
//   public itemIDs : String[] = [
//     "2899f0ba-3a5d-3328-a201-1e1e5a1add31",
//     "81a21568-ae95-38c9-b2fb-a73db05e0154",
//     "82f17a7c-556f-3071-8f24-6eb177e56bfc",
//     "646f2da7-d345-3106-96f1-74deb7223105"
// ];

  public apiCatalog : Item[] ;
  public apiItem : Item;

  constructor(public catalogService :CatalogService){}

  ngOnInit(){
    this.receiveCatalog();
  }

  receiveCatalog(){
    this.catalogService.retrieveCatalog().subscribe(
      (catalog) => { this.apiCatalog = catalog}
    );
  }
  receiveItemByID(id : String){
      this.catalogService.retrieveItemByID(id).subscribe(
        (item : Item) => { this.apiItem = item}
    );
  }
}

