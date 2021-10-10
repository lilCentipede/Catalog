import { Component, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Deployment } from '../model/deployment';
import { Item } from '../model/item';
import { CatalogService } from '../services/catalog.service';

@Component({
  selector: 'app-catalog-card',
  templateUrl: './catalog-card.component.html',
  styleUrls: ['./catalog-card.component.css']
})
export class CatalogCardComponent implements OnInit {
  @Input() public item :Item;
  public entriesOfProperty : Map<String,unknown>
  public form : FormGroup = new FormGroup({});
  public openModal : boolean = false;

  constructor(public catalogService : CatalogService , public form_builder : FormBuilder){
    this.form = form_builder.group({
      projectName : '',
      deploymentName : ''
    })
  }

  ngOnInit(): void {
    this.form.patchValue({
      projectName : this.item.projectIds[0],
      });
  }
  setEntriesOfProperty(){
    const itemProperties = JSON.parse(this.item.schema.properties);
    Object.entries(itemProperties).forEach((entry)=>
     { const [key,value] = entry
      this.entriesOfProperty.set(key,value);
     }
    )
  }
 
  onOpenClick(){
    this.openModal = true;
  }
  submit() : any{
    this.openModal = false;
    var deployBody = {
          "id" : "646f2da7-d345-3106-96f1-74deb7223105" ,
          "body": {
          "bulkRequestCount": 1,
          "deploymentName": "FAKEDeployment",
          "inputs": {},
          "projectId": "5f2cfa61-4c12-4fe5-970b-c304a760b25d",
          "reason": "idk",
          "version": "1"
         }
        };
    deployBody.id=this.item.id;
    deployBody.body.deploymentName = this.form.controls['deploymentName'].value;
    deployBody.body.projectId = this.form.controls['projectName'].value;


    this.catalogService.makeDeployment(deployBody);
}
  
}
