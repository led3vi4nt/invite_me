import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EventModel } from '../../models/event.model';
import { InviteService } from '../../services/invite.service';

@Component({
  selector: 'app-response-view',
  templateUrl: './response-view.component.html',
  styleUrls: ['./response-view.component.css']
})
export class ResponseViewComponent implements OnInit {
    eventDetails: EventModel;
    guestHash: string;
    formGroup: FormGroup;

    constructor(private inviteService: InviteService,
                private router: Router,
                private route: ActivatedRoute) { }

    ngOnInit() {
        this.formGroup = new FormGroup({
            'hash': new FormControl(),
            'guest': new FormControl(),
            'response': new FormControl('IS_GOING', [Validators.required]),
        });
        this.route.paramMap.subscribe(
            paramMap => {
                let hash = paramMap.get('hash');
                this.guestHash = paramMap.get('guest');
                this.inviteService.getEventDetails(hash).subscribe(
                    eventDetails => this.updateDetails(eventDetails),
                    error => console.warn(error),
                );
            },
            error => console.warn(error),
        );
    }

    private updateDetails(eventDetails: EventModel) {
        this.eventDetails = eventDetails;
        this.formGroup.controls.hash.setValue(eventDetails.hash);
        this.formGroup.controls.guest.setValue(this.guestHash);
    }

    sendResponse() {
        this.inviteService.sendResponse(this.formGroup.value).subscribe(
            () => this.router.navigate(['sent'])
        );
    }
}
