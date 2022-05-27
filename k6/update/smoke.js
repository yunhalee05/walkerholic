import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  vus: 1, // 1 user looping for 1 minute
  duration: '10s',

  thresholds: {
    http_req_duration: ['p(99)<100'], // 99% of requests must complete below 1.5s
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
