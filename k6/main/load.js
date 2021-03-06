import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  stages: [
    { duration: '1m', target: 162 }, // simulate ramp-up of traffic from 1 to 15-16 users over 5 minutes.
    { duration: '2m', target: 162 }, // stay at 15-16 users for 10 minutes
    { duration: '10s', target: 0 }, // ramp-down to 0 users
  ],
  thresholds: {
    http_req_duration: ['p(99)<200'], // 99% of requests must complete below 0.1s
  },
};

const BASE_URL = 'https://walkerholic.n-e.kr'

export default function ()  {
  http.get(`${BASE_URL}`);

  sleep(1);
}