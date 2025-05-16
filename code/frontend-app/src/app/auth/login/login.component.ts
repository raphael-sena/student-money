import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterModule],
  providers: [AuthService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  userTypes = ['student', 'company'];

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: () => {
          const role = this.authService.getUserRole();

          if (role === 'STUDENT') {
            this.router.navigate(['/home/student']);
          } else if (role === 'COMPANY') {
            this.router.navigate(['/home/company']);
          } else if (role === 'ADMIN') {
            this.router.navigate(['/home/admin']);
          } else {
            this.router.navigate(['/login']);
          }
        },
        error: (err) => console.error('Erro no login', err)
      });
    }
  }
}
