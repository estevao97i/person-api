import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Pessoa } from "../model/Pessoa";

@Injectable({
  providedIn: 'root',
})
export class Service {

  constructor(private httpClient: HttpClient) {}

  private readonly url = 'http://localhost:8080/';

  sayHello(): Observable<any> {
    return this.httpClient.get<any>(this.url)
  }

  listarPessoas(): Observable<any> {
    return this.httpClient.get<any>(this.url + `api/pessoa`)
  }

  listarPessoaPorCpf(cpf: string): Observable<any> {
    return this.httpClient.get<any>(this.url + `api/pessoa/${cpf}`)
  }

  deletarPessoaPorCpf(cpf: string): Observable<any> {
    return this.httpClient.delete<any>(this.url + `api/pessoa/${cpf}`)
  }

  editarPessoa(pessoa: Pessoa): Observable<any> {
    return this.httpClient.put<any>(this.url + `api/pessoa`, pessoa);
  }

  criarPessoa(pessoa: Pessoa): Observable<any> {
    return this.httpClient.post<any>(this.url + `api/pessoa`, pessoa);
  }

  calculaPesoIdeal(pessoa: any): Observable<any> {
    return this.httpClient.post<any>(this.url + `api/pessoa/calcula-peso`, pessoa);
  }
}
