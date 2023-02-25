import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-tarjetas',
  templateUrl: './tarjetas.component.html',
  styleUrls: ['./tarjetas.component.css']
})

export class TarjetasComponent implements OnInit {

  tarjetas: any;
  dataSource = null;
  displayedColumns: string[] = [];

  constructor(public dataService: DataService) { }

  ngOnInit() {
    this.dataService.getAllTarjetas().subscribe(data =>{
      console.log(data);
      this.tarjetas = data;
    });

    this.dataSource = this.tarjetas;
    this.displayedColumns = ['cedula', 'titular', 'pan', 'tipo', 'telefono', 'estado'];
  }



}

