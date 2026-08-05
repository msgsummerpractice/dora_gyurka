import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './component/not-found';
import { HomeComponent } from './component/home';
import { NgModule } from '@angular/core';
import { authGuard } from './guard/auth-guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  {
    path: 'adopt',
    loadComponent: () => import('./component/adopt').then((m) => m.Adopt),
    canActivate: [authGuard],
  },
  {
    path: 'login',
    loadComponent: () => import('./reactive-login-form/login-form').then((m) => m.LoginForm),
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
