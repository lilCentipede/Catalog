import { Component, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Item } from '../model/item';
import { CatalogService } from '../services/catalog.service';

@Component({
  selector: 'app-catalog-card',
  templateUrl: './catalog-card.component.html',
  styleUrls: ['./catalog-card.component.css']
})
export class CatalogCardComponent implements OnInit {
  @Input() public item :Item;
  public form : FormGroup = new FormGroup({});
  public openModal : boolean = false;

  constructor(public catalogService : CatalogService, public form_builder : FormBuilder){
    this.form = form_builder.group({
      id : '',
      name : '',
      description : ''
    })
  }

  ngOnInit(): void {
    this.form.patchValue({
      id : this.item.id,
      name : this.item.name,
      description: this.item.description
      });
  }

  onOpenClick(){
    
    this.openModal = true;
  }
  submit(){
    this.openModal = false;
   
  }

}
