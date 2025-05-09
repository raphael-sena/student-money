

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, tap} from 'rxjs';
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
  private tokenKey = 'access_token';

  constructor(private http: HttpClient) {}

  /** Autentica usuário e salva o token no localStorage */
  login(data: AuthenticationRequest): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.baseUrl}/login`, data).pipe(
      tap(response => {
        localStorage.setItem(this.tokenKey, response.token);
      })
    );
  }


  /** Cadastra um estudante */
  registerStudent(data: StudentCreateRequestDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register/student`, data);
  }

  /** Cadastra uma empresa */
  registerCompany(data: CompanyCreateRequestDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register/company`, data);
  }

  /** Retorna o token atual salvo */
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  /** Verifica se o token existe e está válido */
  isLoggedIn(): boolean {
    const token = this.getToken();
    if (!token) return false;

    try {
      const [, payloadBase64] = token.split('.');
      const payload = JSON.parse(atob(payloadBase64));
      const now = Math.floor(Date.now() / 1000);
      return payload.exp && payload.exp > now;
    } catch {
      return false;
    }
  }

  getUserRole(): string | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const [, payloadBase64] = token.split('.');
      const payload = JSON.parse(atob(payloadBase64));
      return payload.role || null;
    } catch {
      return null;
    }
  }


  /** Remove o token e efetua logout */
  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
}

