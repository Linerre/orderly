# Orderly
## Introduction
A order-status-checking web application built with Clojure and ClojureScript.

*Note*: This is still work in progress.

## Motivation
I'm working in a small academic library. The staff here rely on Google Workspace (i.e. Gmail, Google Sheets, Google Drive) for everything in their daily routines.

One of my colleagues is responsible for placing book orders and supervise the acquisitions. She uses several spreadsheets to track orders of different types, such as those for course reserves or those requested by faculty for their research.

She alone already starts feeling that the complexity of managing these sheets is ever increasing, not to mention other team members also need to access these sheets for information. For example, when answering patron's queries about a specific order.

It would be helpful if:
1. The information of acquisitions can be collected in a single spreadsheet, within which there might be a few sheets though;
2. Other colleagues just need to check a single place, say, a website where all such info described in #1 is sync-ed over.

This project aims to solve the problem.

## Features
I use "features" because at the moment I can't think of other better words.

- Built with Clojure(Script). Thus, no huge `node_modules` stuff
- Framework-less = Clojure way!

I use Selmer for templating for now, but I may likely switch to Hiccup in the future.
