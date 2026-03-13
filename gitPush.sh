#!/bin/bash

git add .
echo "Provide the git commit message:"
read message
git commit -m $message
git push
