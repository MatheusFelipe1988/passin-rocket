CREATE UNIQUE INDEX events_slugs_key ON events(slugs);
CREATE UNIQUE INDEX attendess_event_id_email_key ON attendess (events_id, email);
CREATE UNIQUE INDEX checkin_attendess_id_key ON checkin (attendess_id);