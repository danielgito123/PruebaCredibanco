import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-transacciones',
  templateUrl: './transacciones.component.html',
  styleUrls: ['./transacciones.component.css']
})
export class TransaccionesComponent implements OnInit {

  transacciones: any;
  dataSource = null;
  displayedColumns: string[] = [];

  constructor(public dataService: DataService) { }

  ngOnInit() {
    this.dataService.getAllTransacciones().subscribe(data =>{
      console.log(data);
      this.transacciones = data;
    });

    this.dataSource = this.transacciones;
    this.displayedColumns = ['pan', 'numreferencia', 'total_compra', 'dir_compra', 'mensaje', 'estado', 'fecha'];
  }

}
