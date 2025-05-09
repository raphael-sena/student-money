import {Component, Input} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';



interface Advantage {
  id?: number;
  title: string;
  description: string;
  discount: number;
  expirationDate: string;
}

@Component({
  selector: 'app-advantage-modal',
  imports: [
    FormsModule
  ],
  templateUrl: './advantage-modal.component.html',
  styleUrl: './advantage-modal.component.css'
})
export class AdvantageModalComponent {
  @Input() advantage: Advantage = {
    title: '',
    description: '',
    discount: 0,
    expirationDate: ''
  };

  constructor(public activeModal: NgbActiveModal) {}

  save(): void {
    this.activeModal.close(this.advantage);
  }

  cancel(): void {
    this.activeModal.dismiss();
  }
}
