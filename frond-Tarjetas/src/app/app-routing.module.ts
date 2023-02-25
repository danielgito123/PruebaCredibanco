import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { TarjetasComponent } from './tarjetas/tarjetas.component';
import { TransaccionesComponent } from './transacciones/transacciones.component';

const routes: Routes = [
  { path: '', redirectTo: 'tarjetas', pathMatch: 'full'},
  { path: 'tarjetas', component: TarjetasComponent },
  { path: 'transacciones', component: TransaccionesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
