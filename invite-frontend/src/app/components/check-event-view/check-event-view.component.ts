import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventModel } from '../../models/event.model';
import { InviteService } from '../../services/invite.service';

@Component({
    selector: 'app-check-event-view',
    templateUrl: './check-event-view.component.html',
    styleUrls: ['./check-event-view.component.css'],
})
export class CheckEventViewComponent implements OnInit {
    eventDetails: EventModel;

    constructor(private inviteService: InviteService,
                private router: Router,
                private route: ActivatedRoute) { }

    ngOnInit() {
        this.route.paramMap.subscribe(
            paramMap => {
                let hash = paramMap.get('hash');
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
    }
}
