import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnvioMoedasComponent } from './components/envio-moedas/envio-moedas.component';
import { ExtratoComponent } from './components/extrato/extrato.component';

const routes: Routes = [
  { path: 'envio-moedas', component: EnvioMoedasComponent },
  { path: 'extrato', component: ExtratoComponent },
  { path: '', redirectTo: '/extrato', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 