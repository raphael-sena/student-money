import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransacaoService, EnvioMoedasDTO } from '../../services/transacao.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-envio-moedas',
  templateUrl: './envio-moedas.component.html',
  styleUrls: ['./envio-moedas.component.css']
})
export class EnvioMoedasComponent implements OnInit {
  envioForm: FormGroup;
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private transacaoService: TransacaoService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.envioForm = this.fb.group({
      idDestinatario: ['', [Validators.required]],
      valor: ['', [Validators.required, Validators.min(0.01)]],
      descricao: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  ngOnInit(): void {
    // Aqui você pode carregar dados adicionais se necessário
  }

  onSubmit(): void {
    if (this.envioForm.valid) {
      this.carregando = true;
      const envioMoedas: EnvioMoedasDTO = {
        idRemetente: '123', // Substituir pelo ID do usuário logado
        idDestinatario: this.envioForm.get('idDestinatario')?.value,
        valor: this.envioForm.get('valor')?.value,
        descricao: this.envioForm.get('descricao')?.value
      };

      this.transacaoService.enviarMoedas(envioMoedas).subscribe({
        next: () => {
          this.snackBar.open('Moedas enviadas com sucesso!', 'Fechar', {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'top'
          });
          this.router.navigate(['/extrato']);
        },
        error: (erro) => {
          this.snackBar.open('Erro ao enviar moedas: ' + erro.message, 'Fechar', {
            duration: 5000,
            horizontalPosition: 'end',
            verticalPosition: 'top'
          });
          this.carregando = false;
        }
      });
    }
  }

  getErrorMessage(campo: string): string {
    if (this.envioForm.get(campo)?.hasError('required')) {
      return 'Este campo é obrigatório';
    }
    if (campo === 'valor' && this.envioForm.get(campo)?.hasError('min')) {
      return 'O valor deve ser maior que zero';
    }
    if (campo === 'descricao' && this.envioForm.get(campo)?.hasError('minlength')) {
      return 'A descrição deve ter pelo menos 10 caracteres';
    }
    return '';
  }
} 