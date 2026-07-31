import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './component/not-found';
import { HomeComponent } from './component/home';
import { LoginComponent } from './component/login';
import { NgModule } from '@angular/core';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', loadChildren: () => import('./component/login').then((m) => m.LoginComponent) },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
