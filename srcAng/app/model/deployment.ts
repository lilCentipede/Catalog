export class Deployment{
    public bulkRequestCount: Number;
    public deploymentName : string;
    public inputs : {};
    public projectId :  string ;
    public reason :  string ;
    public version :  string ; 
    constructor(){
                this.projectId = '';
                this.deploymentName = '';
                this.inputs = ''; 
                this.bulkRequestCount = 0;
                this.reason = '';
                this.version = '';
    }
}