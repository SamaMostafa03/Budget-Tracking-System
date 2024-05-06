#!/bin/bash

set -e

host="$1"
port="$2"
shift 2
cmd="$@"

until nc -z $host $port; do
  >&2 echo "Service $host:$port is unavailable - waiting..."
  sleep 1
done

>&2 echo "Service $host:$port is available - starting command $cmd"
exec $cmd
