import { Component } from '@angular/core';
import {AdvantageModalComponent} from '../../advantage-modal/advantage-modal.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';





interface Advantage {
  id: number;
  title: string;
  description: string;
  discount: number;
  expirationDate: string;
}

@Component({
  selector: 'app-home-company',
  imports: [],
  templateUrl: './home-company.component.html',
  styleUrl: './home-company.component.css'
})
export class HomeCompanyComponent {
  advantages: Advantage[] = [];

  constructor(private modalService: NgbModal) {}

  ngOnInit(): void {
    this.loadAdvantages();
  }

  loadAdvantages(): void {
    // Aqui você pode carregar as vantagens do backend
    // Exemplo estático:
    this.advantages = [
      {
        id: 1,
        title: 'Desconto em Cursos',
        description: '10% de desconto em cursos selecionados.',
        discount: 10,
        expirationDate: '2025-12-31'
      },
      // Adicione mais vantagens conforme necessário
    ];
  }

  openAdvantageModal(advantage?: Advantage): void {
    const modalRef = this.modalService.open(AdvantageModalComponent, { size: 'lg' });
    modalRef.componentInstance.advantage = advantage;

    modalRef.result.then((result) => {
      if (result) {
        if (advantage) {
          // Editar vantagem existente
          const index = this.advantages.findIndex(a => a.id === advantage.id);
          if (index !== -1) {
            this.advantages[index] = result;
          }
        } else {
          // Adicionar nova vantagem
          this.advantages.push({ ...result, id: this.generateId() });
        }
      }
    }).catch((error) => {
      // Modal fechado sem salvar
    });
  }

  deleteAdvantage(id: number): void {
    this.advantages = this.advantages.filter(a => a.id !== id);
  }

  private generateId(): number {
    return this.advantages.length > 0 ? Math.max(...this.advantages.map(a => a.id)) + 1 : 1;
  }
}
