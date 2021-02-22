import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventModel } from '../models/event.model';
import { ResponseModel } from '../models/response.model';
import { RootService } from './root.service';

const BASE_URL = "http://localhost:8080/api/invite_me"

@Injectable({
    providedIn: 'root',
})
export class InviteService {
    newInviteHash: string;

    constructor(private http: HttpClient,
                private root: RootService) { }

    generateNewEventHash() {
        let base = '' + (new Date().getTime() % 100_000_000);
        let prod = '';
        for (let i = 0; i < 8; i += 2) {
            let code = parseInt(base.substr(i, 2)) % 25 + 65;
            prod += String.fromCharCode(code);
        }
        this.newInviteHash = prod;
    }

    recordEvent(data) {
        return this.http.post(BASE_URL, data);
    }

    getEventDetails(hash: string): Observable<EventModel> {
        return this.http.get<EventModel>(BASE_URL + '/' + hash);
    }

    sendResponse(data: ResponseModel) {
        return this.http.post(BASE_URL + '/' + data.hash, data);
    }
}
