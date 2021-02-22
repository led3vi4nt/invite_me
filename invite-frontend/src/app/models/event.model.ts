import { Guest } from './guest.model';

export class EventModel {
    hash: string;
    eventName: string;
    location: string;
    dateTime: string;
    replyAddress: string;
    guestList: Array<Guest>;
    invitationContext: string;
}
