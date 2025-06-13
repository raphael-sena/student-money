import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface EnvioMoedasDTO {
  idRemetente: string;
  idDestinatario: string;
  valor: number;
  descricao: string;
}

export interface TransacaoDTO {
  id: number;
  idRemetente: string;
  idDestinatario: string;
  valor: number;
  descricao: string;
  tipo: string;
  dataHora: string;
}

@Injectable({
  providedIn: 'root'
})
export class TransacaoService {
  private apiUrl = `${environment.apiBaseUrl}/api/transacoes`;

  constructor(private http: HttpClient) { }

  enviarMoedas(envioMoedas: EnvioMoedasDTO): Observable<TransacaoDTO> {
    return this.http.post<TransacaoDTO>(`${this.apiUrl}/enviar`, envioMoedas);
  }

  obterHistorico(idUsuario: string): Observable<TransacaoDTO[]> {
    return this.http.get<TransacaoDTO[]>(`${this.apiUrl}/historico/${idUsuario}`);
  }

  obterSaldo(idUsuario: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/saldo/${idUsuario}`);
  }
} 