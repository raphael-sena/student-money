import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  userTypes = ['estudante', 'empresa'];

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.registerForm = this.fb.group({
      tipoUsuario: ['estudante', Validators.required],
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', Validators.required],
      telefone: ['', Validators.required],
      role: ['STUDENT'],
      address: this.fb.group({
        street:      ['', Validators.required],
        number:      ['', Validators.required],
        complement:  [''],
        neighborhood:['', Validators.required],
        city:        ['', Validators.required],
        state:       ['', Validators.required],
        cep:         ['', Validators.required]
      }),
      cpf:    [''],
      rg:     [''],
      course: [''],
      cnpj:   ['']
    });

    this.registerForm.get('tipoUsuario')!.valueChanges.subscribe(tipo => {
      this.registerForm.patchValue({ role: tipo === 'estudante' ? 'STUDENT' : 'COMPANY' });
    });
  }

  onSubmit() {
    if (this.registerForm.invalid) return;

    const { tipoUsuario, role, name, email, username, password, telefone, address, cpf, rg, course, cnpj } =
      this.registerForm.value;

    const baseUser = { name, email, username, password, telefone, address, role };

    if (tipoUsuario === 'estudante') {
      const payload = {
        person: { user: baseUser, cpf },
        rg,
        course
      };
      this.authService.registerStudent(payload).subscribe({
        next: (res) => console.log('Estudante cadastrado', res),
        error: (err) => console.error(err)
      });
    } else {
      const payload = { user: baseUser, cnpj };
      this.authService.registerCompany(payload).subscribe({
        next: (res) => console.log('Empresa cadastrada', res),
        error: (err) => console.error(err)
      });
    }
  }
}
