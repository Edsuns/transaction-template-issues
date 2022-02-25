# Transaction Template Issues Demo

## Reproduce

- `drop database trx_issues;`
- run maven package lifecycle
- open the console, we can see deadlock error in outputs
- take a look at `DemoService#demo()` to see why there shouldn't be a deadlock
