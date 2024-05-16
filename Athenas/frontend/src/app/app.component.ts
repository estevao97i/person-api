import { Component, OnInit } from '@angular/core';
import { Service } from './service/service';
import { Pessoa } from './model/Pessoa';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {

  pessoas: Pessoa[] = [];
  pessoa!: Pessoa
  edicao: boolean = false;
  showFields: boolean = false;
  form: FormGroup;
  formAtualizar: FormGroup;

  constructor(private service: Service, private formBuilder: FormBuilder, private _snackBar: MatSnackBar) {
    this.form = this.formBuilder.group({
      nome: ['', Validators.required],
      dataNasc: ['', Validators.required],
      cpf: ['', Validators.required],
      sexo: ['', Validators.required],
      altura: [null, Validators.required],
      peso: [null, Validators.required],
    });

    this.formAtualizar = this.formBuilder.group({
      nome: [''],
      dataNasc: [''],
      cpf: [''],
      sexo: [],
      altura: [null],
      peso: [null],
    });
  }

  buscarPorCpf(cpf: string) {
    this.service.listarPessoaPorCpf(cpf).subscribe({
      next: (res: any) => {
        this.pessoa = res;
        this.pessoa.dataNasc = this.formatarDataBrasil(this.pessoa.dataNasc)
        this.edicao = true
        this.formAtualizar.patchValue(this.pessoa)
      },
      error: () => {
        this.formAtualizar.reset();
        this.edicao = false;
      }
    });
  }

  editar() {
    let pessoa: Pessoa = this.formAtualizar.getRawValue();
    pessoa.dataNasc = this.formatarData(pessoa.dataNasc)
    this.service.editarPessoa(pessoa).subscribe({
      next: (res: any) => {
        this._snackBar.open(`Pessoa ${pessoa.nome} foi editado(a)!`, 'ok');
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  excluir(cpf: string) {
    this.service.deletarPessoaPorCpf(cpf).subscribe({
      next: (res: any) => {
        this.edicao = false
        this._snackBar.open(`excluído(a) com sucesso!`, 'ok');
      },
      error: () => {
        this.edicao = true;
      }
    });
  }

  cancelarEdicao() {
    this.edicao = false
  }

  salvar() {
    let pessoa: Pessoa = this.form.getRawValue();
    pessoa.dataNasc = this.formatarData(pessoa.dataNasc)
    this.service.criarPessoa(pessoa).subscribe({
      next: () => {
        this._snackBar.open(`Pessoa ${pessoa.nome} foi criado(a)!`, 'ok');
        this.form.reset()
        this.showFields = false
      },
      error: (e) => {
        console.log(e);
        this._snackBar.open(`Pessoa com o CPF já inserido.`, 'ok');
      }
    });
  }

  calculaPesoIdeal() {
    const filtro = {
      peso: this.formAtualizar.value.peso,
      altura: this.formAtualizar.value.altura,
      sexo: this.formAtualizar.value.sexo,
    }

    this.service.calculaPesoIdeal(filtro).subscribe({
      next: (value) => {
        const pesoIdeal = String(value);
        this._snackBar.open(`O peso ideal seria de: ${pesoIdeal.substring(0,6)}Kg`, 'ok');
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  formatarData(data: string): string {
    if (data.includes('/')) {
      let newDate = '';
      newDate = `${data.substring(6,10)}` + `-` + `${data.substring(3,5)}` + `-` + `${data.substring(0,2)}`
      return newDate;
    }
    return data;
  }

  formatarDataBrasil(data: string): string {
      let newDate = '';
      newDate = `${data.substring(8,10)}` + `/` + `${data.substring(5,7)}` + `/` + `${data.substring(0,4)}`;
      return newDate;
  }
}
