import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GetTokenComponent } from './get-token/get-token.component';
import { CallResourcesComponent } from './call-resources/call-resources.component';

const routes: Routes = [
  { path: 'getToken', component: GetTokenComponent },
  { path: 'callResources', component: CallResourcesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
