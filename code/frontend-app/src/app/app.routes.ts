import { Routes } from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {HomeStudentComponent} from './components/pages/home-student/home-student.component';
import {HomeCompanyComponent} from './components/pages/home-company/home-company.component';

export const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'login',
    loadComponent: () => import('./auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'register',
    loadComponent: () => import('./auth/register/register.component').then(m => m.RegisterComponent)
  },

  { path: 'home/student', component: HomeStudentComponent },
  { path: 'home/company', component: HomeCompanyComponent },


];
