import { Component, OnInit } from '@angular/core';
import { TransacaoService, TransacaoDTO } from '../../services/transacao.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css']
})
export class ExtratoComponent implements OnInit {
  transacoes: TransacaoDTO[] = [];
  saldo: number = 0;
  carregando = true;

  constructor(
    private transacaoService: TransacaoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    const idUsuario = '123'; // Substituir pelo ID do usuário logado
    
    this.transacaoService.obterHistorico(idUsuario).subscribe({
      next: (transacoes) => {
        this.transacoes = transacoes;
        this.carregarSaldo(idUsuario);
      },
      error: (erro) => {
        this.snackBar.open('Erro ao carregar histórico: ' + erro.message, 'Fechar', {
          duration: 5000,
          horizontalPosition: 'end',
          verticalPosition: 'top'
        });
        this.carregando = false;
      }
    });
  }

  carregarSaldo(idUsuario: string): void {
    this.transacaoService.obterSaldo(idUsuario).subscribe({
      next: (saldo) => {
        this.saldo = saldo;
        this.carregando = false;
      },
      error: (erro) => {
        this.snackBar.open('Erro ao carregar saldo: ' + erro.message, 'Fechar', {
          duration: 5000,
          horizontalPosition: 'end',
          verticalPosition: 'top'
        });
        this.carregando = false;
      }
    });
  }

  formatarData(data: string): string {
    return new Date(data).toLocaleDateString('pt-BR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  formatarValor(valor: number): string {
    return valor.toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    });
  }
} 