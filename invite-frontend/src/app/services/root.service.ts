import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class RootService {

    BASE_URL = 'http://localhost:4200';

    constructor() { }
}
