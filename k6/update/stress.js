import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
      { duration: '30s', target: 100 }, // below normal load
      { duration: '1m', target: 100 },
      { duration: '30s', target: 200 }, // normal load
      { duration: '1m', target: 200 },
      { duration: '30s', target: 300 }, // around the breaking point
      { duration: '1m', target: 300 },
      { duration: '30s', target: 400 }, // beyond the breaking point
      { duration: '1m', target: 400 },
      { duration: '2m', target: 0 }, // scale down. Recovery stage.
    ],
    thresholds: {
      http_req_duration: ['p(99)<100'], // 99% of requests must complete below 0.1s
    },
  };
  
const BASE_URL = 'https://walkerholic-backend.o-r.kr'
const USERNAME = 'testUser1@example.com';
const PASSWORD = '123456';
const POST_ID = '1';
const USER_ID = '1';
const TITLE = 'testupdate';
const CONTENT = "test update content";

export default function ()  {

var payload = JSON.stringify({
    username: USERNAME,
    password: PASSWORD,
    });

    var params = {
    headers: {
        'Content-Type': 'application/json',
    },
    };

    let loginRes = http.post(`${BASE_URL}/api/signin`, payload, params);
    check(loginRes, {'logged in successfully': (resp) => resp.json('token') !== '',});


    let authHeaders = {
    headers: {
        Authorization: `Bearer ${loginRes.json('token')}`,
        'Content-Type': 'application/json',
    },  
    };

  const postRequest = JSON.stringify({
    title : TITLE,
    content: CONTENT,
    userId: USER_ID
})

  const postRes = http.put(`${BASE_URL}/api/posts/${POST_ID}`, postRequest, authHeaders);
  check(postRes, { 'updated post successfully': (resp) => resp.status === 200 });
  sleep(1)
};
