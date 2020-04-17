import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Poll } from '../domain/poll';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class PollApiService {
    baseURL = 'http://localhost/v1/poll-svc';

    constructor(private http: HttpClient) { }

    getPolls(): Observable<Poll> {
        return this.http.get<Poll>(this.baseURL + '/polls')
            .pipe(
                retry(1),
                catchError(this.handleError)
            )
    }

    vote(poll: string, choice: number): Observable<Poll> {
        return this.http.put<Poll>(this.baseURL + `/polls/${poll}/choices/${choice}`, {})
            .pipe(
                retry(1),
                catchError(this.handleError)
            )
    }

    // Error handling 
    handleError(error) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
            // Get client-side error
            errorMessage = error.error.message;
        } else {
            // Get server-side error
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        window.alert(errorMessage);
        return throwError(errorMessage);
    }

}