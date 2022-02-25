# Transaction Template Issues Demo

## Setup

make sure your mysql 8 running at `localhost:3306`

## Reproduce

1. `drop database trx_issues;`
2. run maven test lifecycle
3. open the console, we can see deadlock error in outputs
4. take a look at `DemoService#demo()` to see why there shouldn't be a deadlock
