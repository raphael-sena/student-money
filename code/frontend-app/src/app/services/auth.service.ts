

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

/** Typing for authentication request */
export interface AuthenticationRequest {
  email: string;
  password: string;
  tipoUsuario: 'estudante' | 'empresa';
}

/** Address DTO */
export interface AddressCreateRequestDTO {
  street: string;
  number: string;
  city: string;
  state: string;
  zipCode: string;
}

/** User DTO */
export type Role = 'ADMIN' | 'STUDENT' | 'PROFESSOR' | 'COMPANY';

export interface UserCreateRequestDTO {
  name: string;
  email: string;
  username: string;
  password: string;
  address: AddressCreateRequestDTO;
  role: Role;
}

/** Student DTO */
export interface PersonCreateRequestDTO {
  user: UserCreateRequestDTO;
  cpf: string;
}

export interface StudentCreateRequestDTO {
  person: PersonCreateRequestDTO;
  rg: string;
  course: string;
}

/** Company DTO */
export interface CompanyCreateRequestDTO {
  user: UserCreateRequestDTO;
  cnpj: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.apiBaseUrl + '/auth';

  constructor(private http: HttpClient) {}

  /**
   * Autentica usuário (estudante ou empresa)
   */
  login(data: AuthenticationRequest): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, data);
  }

  /**
   * Cadastra um estudante
   */
  registerStudent(data: StudentCreateRequestDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register/students`, data);
  }

  /**
   * Cadastra uma empresa
   */
  registerCompany(data: CompanyCreateRequestDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register/company`, data);
  }
}
